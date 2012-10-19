(ns shouts.core
  (:use
    [compojure.core :only (defroutes GET)]
    [compojure.route :only (not-found)]
    [joodo.middleware.view-context :only (wrap-view-context)]
    [joodo.views :only (render-template render-html)]
    [joodo.controllers :only (controller-router)]
    [ring.util.response]
    [hyperion.api]
    [hyperion.sqlite]))

(set-ds! (new-sqlite-datastore "jdbc:sqlite:db/shoutsDB.sqlite"))

(defroutes shouts-routes
  (GET "/" [] (redirect "/shouts"))
  (controller-router 'shouts.controller)
  (not-found (render-template "not_found" :template-root "shouts/view" )))

(def app-handler
  (->
    shouts-routes
    (wrap-view-context :template-root "shouts/view" )))

