# Base.java
Bāzes klase.
Klase Base.java glabājas 
* Visi pievienotie objekti `com.anabode.fw.GameObject` 
* Box2D fizikas bāzes klase `com.badlogic.gdx.physics.box2d.World`
* Kamera `com.badlogic.gdx.graphics.OrthographicCamera` 
* UI bāzes klase `com.badlogic.gdx.scenes.scene2d.Stage`

<br>
## Game-Loop
Šai klasei ir 2 galvenās funkcijas kas jāizsauc main-loopā
1. `base.update()`
2. `base.render()`

#### `update()`
`update()` metode iziet cauri visiem objektiem(`com.anabode.fw.GameObject`) un izsauc viņu `update()` metodi.
#### `render()`
`render()` metode iziet cauri visiem objektiem(`com.anabode.fw.GameObject`) un izsauc viņu `render()` metodi.

### Objektu pievienošana \ iegūšana \ dzēsšana
<br>Lai pievienotu objektu game-loopam jāizsauc bāzes metode `base.addObject(GameObject object)`.
<br> Pēc pievienošanas objekts tiek inicializēts un inicializēti tiek visi viņa skripti.
<br> Lai iegūtu objektu no kopēja poola, objektam jābut uzstādītam parametram `name`  un jāizsauc metode `base.getObject(String objectName);`
<br> Lai dzēstu objektu jāizsauc metode `base.destroyObject(GameObject object);`, pēc izdzēšanas objektam tiek izsaukta `dispose()` metode.

### Parametru uzstādīšana 
Izmantojot pieejamās metodes `Base.java` klasē var modificēt fizikas gravitāciju, kameras viewportu, ui viewportu 

# GameObject
GameObject.java ir abstrakta klase kuru manto visi spēles objekti.
<br> GameObject visi nepieciešamie atribūti, skripti tiek pievienoti metodē `create()`, šī metode tiek izsaukta kad objekts tiek pievienots game-loopam.

### Atribūtu pievienošana 
Lai pievienotu atribūtu objektam jāizsauc metode `addAttribute(String name, Object data)`.
<br> katram atribūtam ir savs nosaukums lai pēc tam tos varētu iegūt no objekta.
<br> Ja atribūts ir vai manto klasi `com.badlogic.gdx.physics.box2d.Body` pie pievienošanas objektam atribūtam tiek uzstādīts userData tas objekts pie ka tiek pievienots šis atribūts.
<br> Ja atribūts ir vai manto klasi `com.badlogic.gdx.scenes.scene2d.Actor` objekts tiek atzīmēts ka UI objekts un tiek pievienots pie UI objektiem,  

### Skriptu pievienošana 
Lai pievienotu skriptu objektam jāizsauc metode `addScript(ActionScript script)`.
<br> Ja objekts jau ir inicializēts skripts tiks automatiski inicializēts, ja ne tad tas notiks pie objekta inicializācijas.
<br> Ja skripts implementē klasi `InputProcessor.java` skripts tiks pievienots InputProcessoru poolam un skripts varēs izmantot visas input metodes.


# ActionScript
ActionScript ir abstrakta klase kuru manto visi skripti kas tiek pievienoti spēles objektiem.
ActionScript klasei ir vairākas metodes kas tiek izsauktas balstoties uz notikumiem kas skar objektu kam skripts ir pievienots.

#### initialize()
Šī metode tiek izsaukta kad skripts tiek pievienots objektam, vai kad objekts tiek inicializēts.
<br>Šajā metodē nepieciešams iegūt visus nepieciešamos parametrus no objekta lai tas nebūtu jādara katrā iterācijā.
#### onRender(SpriteBatch batch)
Šī metode tiek izsaukta kad objekts tiek renderots.

#### onUpdate()
Šī metode tiek izsaukta katru kadru ja skripts ir enabled. Šeit būtu jāveic loģika kas ir neatkarīga no ārējā inputa.
#### onTouchDown() 
Šī metode tiks izsaukta ja objektam ir atribūts 'com.badlogic.gdx.physics.box2d.Body' un lietotājs ir piespiedis peli/pirkstu uz šo objektu.

#### onTouchUp()
Šī metode tiek izsaukta ja objektam ir atribūts 'com.badlogic.gdx.physics.box2d.Body' un lietotājs ir pirms tam ir piespiedis peli/pirkstu uz šo objektu un šajā mirkli to atlaiž.

####  onTouchDragged()
Šī metode tiek izsaukta ja objektam ir atribūts 'com.badlogic.gdx.physics.box2d.Body' un lietotājs ir pirms tam ir piespiedis peli/pirkstu uz šo objektu un neatlaižot velk pa ekrānu.

#### onPeriodicUpdate() 
Šī metode tiek izsaukta tikai tad ja skriptam ir uzstādīts mainīgais period un ir lielāks par 0 un laiks kopš pēdējās update reizes ir lielāks par uzstādīto periodu.

#### onCollision(final GameObject from) 
Šī metode tiek izsaukta ja objektam ir atribūts 'com.badlogic.gdx.physics.box2d.Body' un viņš saskaras ar citu objektu kuram ir šāds atribūts.
#### onGuiTouch() 
Šī metode tiek izsaukta ja objekts ir uzstādīts ka UI un tiek uzspiests uz šo objektu.
