FROM python:alpine

ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1
ENV DJANGO_SUPERUSER_PASSWORD admin


RUN pip install --upgrade pip
#RUN pip install -r requrements.txt
RUN pip install django junicorn

CMD python3 manage.py makemigrations --noinput &&\
    python3 manage.py migrate --noinput &&\
    python3 manage.py collectstatic --noinput &&\
    python3 manage.py createsupeuser --user admin --email admin@gmail.com --noinput; \
    gunicorn -b 0.0.0.0:8000 config.wsgi
