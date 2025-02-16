FROM node:16.17.0 as frontend

WORKDIR /app

COPY ./expense-app/package.json package.json
COPY ./expense-app/package-lock.json package-lock.json

COPY ./expense-app/ .

EXPOSE 3000
CMD [ "npm", "run", "start" ]
