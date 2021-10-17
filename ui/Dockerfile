FROM node:14 as env
FROM env as build
COPY . src
WORKDIR src
RUN   npm install && \
      npm run build
FROM build as test
RUN   npm run lint && \
      npm run test
FROM nginx AS release
COPY --from=build src/public/ /usr/share/nginx/html/ui/
COPY --from=build src/dist/ /usr/share/nginx/html/ui/
EXPOSE 80