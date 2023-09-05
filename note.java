

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
roye port 3306 expose mikonim
dar volumes directory dbdata ra b /var/lib/mysql bind mikonim (in directory haman poshei ast k etelaat mysql dar an vojod darad
k ma dbdata khod ra b an bind mikonim //yani inke in directory roye image mariadb ra datash ra b dbdata ma vasl kon)


