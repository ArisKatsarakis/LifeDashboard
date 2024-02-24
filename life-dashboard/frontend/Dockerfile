FROM node:16.17.0 as frontend

WORKDIR /app

COPY ./expense-app/package.json package.json
COPY ./expense-app/package-lock.json package-lock.json

RUN npm install 


COPY ./expense-app/ .

CMD [ "npm", "run", "start" ]
