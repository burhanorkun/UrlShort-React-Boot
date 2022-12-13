FROM node:16.14.2

MAINTAINER Burhan Orkun <burhanorkun@gmail.com>

RUN mkdir -p "/product/front"

COPY . /product/front

WORKDIR /product/front
RUN npm i

CMD ["npm", "start"]
