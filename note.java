

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


