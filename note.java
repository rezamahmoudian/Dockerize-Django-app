"image"
templati az docker k roye system ma vojod darad


"container"
imagi k run shode bashad va darhale estefade az an bashim


"docker ps"
neshan dadan container haye darhal ejra


"docker ps -a"
container hayi k ghablan run kardim va alan run nist ro ham b ma neshon mide


"docker run -d ..."
-d baes mishavad container ma diatach shavad va dar pas zamine run shavad va ba baste shodan terminal baste nashavad


"docker run -d --name <name>"
dadan name b container
dar in sorat container name mara migirad na name randomi k khode docker b an midahad


"docker stop <id> or <name>"
stop kardan container
age chand ta name ya id posht sar ham bzanim hame stop mishan


"docker stop $(docker ps -q)"
stop kardan hameye container ha
docker ps -q id hameye container haye darhal ejra ra b ma midahad


"docker start $(docker ps -aq)" //fek konam in behtar bashe "docker start $(docker images -q)"
start kardan hame container ha


"docker rm <id> or <name>"
hazf container

"docker rm $(docker ps -aq)"
hazf hame container ha


"docker iamges"
list image ha


"docke rmi <id> or <name>"
hazf image


"docker save -o home/rezaM/... nginx"
zakhireye image gofte shode dar system


"docke load -i home/rezaM/Desktop/nginx-image"
load kardan image k az ghabl dar system zakhire kardim


"docker pull nginx or nginx:alpine or <any image>"
pull kardan image


"docker run -d --name <name> -p 8080:80 nginx"
system ma port darad va har container ham port darad ma bayad moshakhas konim k vaghti b felan port az system ma darkhast dade shod
felan port dar felan container fara khonde shavad
-p 8080:80 yani inke agar b port 8080 system darkhast dade shod hamon port 80 nginx fara khonde shavad


dastresi b bash containeri k diatach kardim va dar pas zamine dahal ejrast:
"docker exec -it <name> or <id> bash"
vared shodan b bash container
deghat konid k ba zadan in dastor shoma digar daron system khodeton nistid va vared container shodid va masalan agar
dastor cat /etc/os-release ro vared konid system amel container namayesh dade mishavad na system shoma
ya masalan ls bgirim file haye container ra mibinim


"docker exec -it <nginx:alpine> sh"
chon noskeye alpine bash nadare mishe az in dastor estefade kard va b shell on vasl shod


"volume"
hamontor k ghablan goftim vaghti ma yek container darim va darhal ejrast va vared on mishim dige toye system khodemon
nistim va faghat b filehaye container dastresi darim va agar bkhaym file khodemon toye in container baramon ejra she
masalan age bkhaym bjaye safhe home pishfarz nginx file home site khodemon bala biyad bayad file hayi k darim ro toye
container jaygozin file haye pishfarz konim k khob kare sakhtiye va ba harbar bastan container on ha ham hazf mishan

rahe behtar estefade az volume hast
volume yek directory toye system mast k b yek directory dar container bind mishe va on container miyad va on directorish ro
b in directory dar system ma motasel mikone va ma vaghti vared on directory az container mishim engar k vared directorye az
system khodemon shodim k b onvan volume entekhabesh kardim
har taghyiri k toye volume bdim toye on directory az container k b on bind shode ham emal mishe

asan engar volume toye container gharar migire


"docker run -d -p 8000:80 -v /home/rezaM/Desktop/template:/usr/share/nginx/html nginx"
-v baraye neshan dadan volume ast
bad az -v bayad address directorie system khodemon ro vared konim va bad az on moshakhas konim k b koja bind shavad
yani alan template ro b /user/share/nginx/html bind kardim va hala ba ejraye nginx site ma bala miayad


"docker run -d -p 8000:80 -v /home/rezaM/Desktop/template:/usr/share/nginx/html:ro nginx"
hamontor k goftim vaghti ma az volume estefade mikonim volume ma b container bind mishe va har taghyiri k toye
volume bdim toye container ham taghyir mikone(aslan khodeshe k rafte to container) vali in masale ham vojod dare k
age ma vared container shim va onja in file haye volume ro taghyir bdim on file ha toye system ma ham taghyir mikonan
masalan mitonim az toye container hame file haro taghyir bdim k khob in khob nist va baraye jelogiri az in etefagh
bad az moshakhas kardan volume :ro mizarim ta read only bashan va toye container nashe on haro taghyir dad


"Dockerfile"
docker file -- build ---> image -- run ---> container

sample Dockerfile:
    FROM nginx:alpine
    Add ./templates /usr/share/nginx/html
in docker file miyad va az image nginx noskheye alpine estefade mikonad va file templates ro toye directory neveshte
shode az in image mirize
ma mitonim az in dockerfile build bgirim va yek image jadid ba name delkhah khodemon besazim k dar image jadid ma
file haye templates toye directory /usr/share/nginx/html vojod daran
baraye build gereftan az dastor zir estefade mikonim
"docker build -t <name>:<version>"
in dastor bayad dar haman directory k Dockerfile gharar darad vared shavad

hata mitonim az yek dockerfile chand image bsazim
"docker build -t website:latest -t website:0.0.1"
az docker file ma do image ba name website dade mishavad k version yeki latest hast va version dige adadi neveshte mishavad

"RUN vs CMD"
dar docker file dastorati k baraye sakht image niaz darim ra ba dastor RUN ejra mikonim vali dastorati k niaz nist
dar image ejra shavand va faghat vaghti k ma image ra run kardim va b container tabdil shod ejra shavand
(mesl ejraye gunicorn ya start project) bayad anhara ba dastor CMD ejra konim


"docker-compose"
ma dar yek project az chandin image estefade mikonim
che image haye az ghabl sakhte shode mesl mysql
che image hayi k khodemon ba docker file misazim mesl app ya web server ma
pas ma yek image nadarim va chand image darim k bayad dar yek network b tor khas va ba yek seri ravabet khas
kar konnad
baraye in hadaf behtarin kar estefade az docker compose hast

agar docker file dashte bashim toye docker compose bayad az on buil bgirim 'build:<directory k Docker file toye on gharar dare>'
vali age docker file nadashtim va mikhastim mostaghiman az image estefade konim b in shekl minevisim 'image:<esme image>'

az docker compose baraye in estefade mikonim k digae niazi b zadan dastorat docker nabashad masalan b jaye
zadan dastor -docker run -d -p 8000:80 --name website nginx:alpine
in eteleat ra vared file docker-compose mikonim
    version '3'
    services:
        image: nginx:alpine
        container_name: website
        ports:
          -8000:80

//in dastorat bayad dar directorie zade shavand ke docker-compose.yml dar an vojod dashte bashad
"docker-compose up"
start kardan container haye mojod dar docker compose


"docker-compose up -d"
run shodan container ha b sorat diatach va bdon niaz b baz negah dashtan terminal


"docker-compose up --build"
vaghti dar docker-compose ma niaz b build gereftan bod bayad in dastor ra bzanim
vaghti build gerefta shod agar docker-compose ra down kardin baraye up dobare niaz b build nist
chon ghablan image az ha buil gerefte shod


"docker-compose down"
ham container ra stop mikonad ham anra hazf mikonad



' ''
dockerize django app
'''

"app media static dbdata"(etelaat databse) bayad dar directorihaye khas zakhire shavand chon gahi oghat niyaz ast
b anha dastresi dashte bashim va masalan backup anha ra dar server digar zakhire konim,...
agar in 4 directiory ra dashte bashim baghiyeye project mohem nist va ba dashtan in 4 directory agar
moshkeli baraye project rokh dahad mitavanim in 4 directory ra bardashte va project ra dobare run mikonim

--python manage.py collectstatic '--noinput'
/noinput/ baes mishavad k agar bad az command niyaz b confirm bod masalan yes niyaz bod bznim
khodesh handel konad va yes bzanad
"noinput" ro dar hameye sharayet behtar ast bzarim baraye ehtiyat

"create superuser"
bayad username va email va password ra dar command khod bdahim
baraye dadan password bayad az command zir estefade konim va password ra zakhire konim

--export DJANGO_SUPERUSER_PASSWORD=password
--python manage.py createsuperuser --noinput --user admin --email admin@gmail.com

user name va email ra mostaghiman dar command minevisim va '--noinput' ham password ra az moteghayere mohiti k
zakhire karde bodim migirad va baraye password migozarad


"default.conf"
yek file hast k config haye asli nginx dar an gharar migirand va bayad dar file nginx sakhte shavad


"docker-compose.yml"
type bind yani inke directioryi k toye syestem mn hast bind she b directoryi k roye image hast


"DockerFile"
--ENV PYTHONDONTWRITEBYTECODE:
    python dar avalin run yek seri file pyc misazad k compile shode va cache hastand
    run shodan project yekam kond mishe vali sorat ejraye barname bishtar mishavad
    vali ma b an ehtiyaj nadarim dar in project pas an ra barabar 1 gharar midahim

--ENV PYTHONUNBUFFERED:
    dar noinput miyayad va vorodi va khoroji hara handle mikonad

--ENV DJANGO_SUPERUSER_PASSWORD
    password superuser ra set mikonim


directorie k mikhahad bind shavad (target) ra misazim k haman /app ast
--RUN mkdir/app
--WORKDIR /app

python3 manage.py createsupeuser --user admin --email admin@gmail.com --noinput;
; entehayi baes mishavad k agar in khat error dad bazham khathaye badi ejrashavand


"nginx/dockerfile"
az image nginx:alpine estefade mikonad va config defalt image ra pak mikonad va configi k khodemon neveshtim ro
b on directory ezafe mikone

"default.conf"
upstream:
    dar upstream ma bayad esme service ya container mojod dar docker-compose ra bnvisim va porti az in service k bayad
    listen shavad ro ham jelosh minevisim
yel upstream b esme app misazim va migim k in upstream port 8000 ro gosh kone va on app ro roye port 80 proxy kone
in upstream ra bayad b allowed host ezafe konim

location /media {
    alias /app/media
}
harvaght varkhast b media omad az app/media anhara bekhan (in directory haman directorir ast k dar docke compose
neveshtim)


'''
add mariadb docker settings
'''
"docker-compose"
dar docker compose ma bayad yek mariadb ra ezafe konim

 mariadb:
    image: 'mariadb:latest'
    container_name: 'mariadb'
    restart: 'always'
    expose:
      - '3306'
    environment:
      - "MARIADB_DATABASE=django_app"
      - "MARIADB_USER=django_user"
      - "MARIADB_PASSWORD=1234"
      - "MARIADB_ROOT_PASSWORD=1234"
    volumes:
      - type: 'bind'
        source: './volumes/dbdata'
        target: '/var/lib/mysql'

b jaye build inbar bayad az image estefade konim va image mariadb ra b an bdahim
container_name ra mariadb gharar midahim
restart always yani inke agar b error khord khodesh restart beshe
roye port 3306 expose mikonim
dar volumes directory dbdata ra b /var/lib/mysql bind mikonim (in directory haman poshei ast k etelaat mysql dar an vojod darad
k ma dbdata khod ra b an bind mikonim //yani inke in directory roye image mariadb ra datash ra b dbdata ma vasl kon)
inkar baes mishe k vahgti ma mikaym project ro restart konim etelat ma dar dbdata baghimimanand va database ma pak nmishavad

"mysqlclient"
baraye vasl shodan project django b database mysql ya mariadb ma bayad az pakage 'mysqlclient' estefade konim
in pakage =mysqlclient= khodesh b tanhayi ghabel nasb nist va b yek pakage vaset baraye nasb niaz darad
dar debyan va ubonto in pakage vaset  lib mariadb client-def hast
dar alpine bayad az in command ha estefade konim
-RUN apk update
-RUN apk add --no-cache gcc python3-dev musl-dev mariadb-dev
'--no-cache'yani inke filehaye download shode ro negahdari nakon
3 pakage aval yani (gcc python3-dev musl-dev)baraye nasb mariadb-dev niaz hastand vali bad az nasb mariadb-dev digar niazi
b anha nist va baraye payin bordan hajm image dar nahayat anhara hazf mikonim
-RUN apk del gcc python3-dev musl-dev
// in dastorat ra bayad dar docker file ezafe konim

dar marhale badi ma bayad setting project django ro taghyir bdahim va database mysql ra b onvan database pishvarz project
gharar dahim

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': os.environ.get("MARIADB_DATABASE"),
        'USER': os.environ.get("MARIADB_USER"),
        'PASSWORD': os.environ.get("MARIADB_PASSWORD"),
        'HOST': os.environ.get("MARIADB_HOST")
    }
}
in moteghayere haye mohiti(environment) ha ra ma dar container app dar docker compose set mikonim va sepas ba dastore os.environ
az anha estefade mikonim va barabar ba maghadir setting khod gharar midahim


"depends_on"
vaghto yek image b image haye digar niaz dashte bashad bayad dar docker compose vaghti an image ra misazim
dar ghesmate depends_on moshakhas konim k b che image haye digari niaz darad
b onvan mesal app b nginx va mariadb niaz darad vali mariadb va nginx b image digari niaz nadarand


"moshkel ejraye ham zaman container ha"
container app hatman bayad bad az ejraye kamel container mariadb ejra shavad va agar hamzaman ba an ejra shavad b error mikorim
baraye hal kardan in moshkel mitavanim az rah haye zir estefade konim
-CMD sleep 10
yani 10s sabr kon ta dar an 10s container mariadb b tor kamel ejra shavad

vali ravesh behtar ine k commandi k b ma error mide ro peyda konim va bgim k ta vaghti in command error mide dobare khodesh
ro ejra kon injori  inghad in command ejra mishe va error mikore ta mariadb b tor kamel ejra she va bad on command error nmikhore
va baghiyeye marahel tey mishan

-while ! python3 manage.py migrate --noinput ; do sleep 1; done
vaghti yek command b error mikore baraye ma false barmigardone
pas ma in shart ro gozashtim k ta vaghti k maquse command true bod (yani command false bargardond) 1 saniye sleep kon va dobare
emtehan kon ta vaghti k command b error bokhore va false bargardone dar in halghe gir mikonim ta command b dorosti ejrashavad

baraye inke error haye marbot b in talashha baraye ma namayesh dade nashavand bayad az dastore zir estefade konim
-while ! python3 manage.py migrate --noinput > /dev/null 2>&1; do sleep 1; done
in command miyad va result in dastor ro dar fazaye dev/null mirizad va khoroji mahv mishe vali error ha namayesh dade mishan
va baraye mahv shodan error ha ba dastor 2>&1 error haro b terminal1 redirect mikonim va intori kamelan mahv mishan

dar in halat yek mishkel b vojod miyad va onam ine k migration haye anjam shode ham b ma namayesh dade nmishan chon khodemon
goftim k namayesh dade nashe
baraye hale in moshkel ma mitonim in try ha baraye vasl shodan database ro ba dastor digeii anjam bdim b jaye dastor migrate
-while ! python3 manage.py sqlflush > /dev/null 2>&1; do sleep 1; done
dastore 'sqlflush' table haye mojod dar database ro b ma namayesh mide pass baraye ejraye on mysql bayad amade bashe
pas kheyli rahat mitonim az on baraye test amade bodan mysql estefade konim va baraks migration khoroji on ro ham niaz nadarim
va ba namayesh nadadanesh ham moshkeli nadarim




??
expose:
    yani containter ma dar in port faghat be sorat local va mahali dar dastres bashad va az kharegh b on dastresi nadashte bashim
    masalan container nginx dar ba estefade az port 3306 mariadb b on dastresi dashte bashe vali ma b tor mostaghim az khareg
    app ba estefade az port 3306 b on dastresi nadashte bashihim