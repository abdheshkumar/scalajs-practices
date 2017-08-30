package tutorial.webapp.react
import japgolly.scalajs.react._, vdom.all._
import scala.scalajs.js
object PictureAppExample {
  case class Picture(id: String, url: String, src: String, title: String, favorite: Boolean = false)

  case class State(pictures: List[Picture], favourites: List[Picture])

  type PicClick = (String, Boolean) => Callback

  class Backend($: BackendScope[Unit, State]) {

    def onPicClick(id: String, favorite: Boolean) =
      $.state flatMap { s =>
        if (favorite) {
          val newPics = s.pictures.map(p => if (p.id == id) p.copy(favorite = false) else p)
          val newFavs = s.favourites.filter(p => p.id != id)
          $.modState(_ => State(newPics, newFavs))
        } else {
          var newPic: Picture = null
          val newPics = s.pictures.map(p => if (p.id == id) {
            newPic = p.copy(favorite = true); newPic
          } else p)
          val newFavs = s.favourites.+:(newPic)
          $.modState(_ => State(newPics, newFavs))
        }
      }

    def render(s: State) =
      div(
        h1("Popular Instagram Pics"),
        pictureList((s.pictures, onPicClick)),
        h1("Your favorites"),
        favoriteList((s.favourites, onPicClick)))
  }

  val picture = ScalaComponent.builder[(Picture, PicClick)]("picture")
    .render_P { case (p, b) =>
      div(if (p.favorite) cls := "picture favorite" else cls := "picture", onClick --> b(p.id, p.favorite))(
        img(src := p.src, title := p.title)
      )
    }
    .build

  val pictureList = ScalaComponent.builder[(List[Picture], PicClick)]("pictureList")
    .render_P { case (list, b) =>
      div(`class` := "pictures")(
        if (list.isEmpty)
          span("Loading Pics..")
        else
          list.map(p => picture.withKey(p.id)((p, b))).toVdomArray
      )
    }
    .build

  val favoriteList = ScalaComponent.builder[(List[Picture], PicClick)]("favoriteList")
    .render_P { case (list, b) =>
      div(`class` := "favorites")(
        if (list.isEmpty)
          span("Click an image to mark as  favorite")
        else
          list.map(p => picture.withKey(p.id)((p, b))).toVdomArray
      )
    }
    .build

  val PictureApp = ScalaComponent.builder[Unit]("PictureApp")
    .initialState(State(Nil, Nil))
    .renderBackend[Backend]
    .componentDidMount(scope => Callback {
      // make ajax call here to get pics from instagram
      import scalajs.js.Dynamic.{global => g}
      def isDefined(g: js.Dynamic): Boolean =
        g.asInstanceOf[js.UndefOr[AnyRef]].isDefined
      val url = "https://api.instagram.com/v1/media/popular?client_id=642176ece1e7445e99244cec26f4de1f&callback=?"
      g.jsonp(url, (result: js.Dynamic) => {
        if (isDefined(result) && isDefined(result.data)) {
          val data = result.data.asInstanceOf[js.Array[js.Dynamic]]
          val pics = data.toList.map(item => Picture(item.id.toString, item.link.toString, item.images.low_resolution.url.toString, if (item.caption != null) item.caption.text.toString else ""))
          scope.modState(_ => State(pics, Nil)).runNow()
        }
      })
    })
    .build
}
