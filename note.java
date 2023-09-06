

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