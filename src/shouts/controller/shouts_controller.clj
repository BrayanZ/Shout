(ns shouts.controller.shouts-controller
  (:use
    [compojure.core :only (defroutes GET POST)]
    [compojure.route :only (not-found)]
    [joodo.middleware.view-context :only (wrap-view-context)]
    [joodo.views :only (render-template render-html)]
    [joodo.controllers :only (controller-router)]
    [shouts.shouts.interactor :as interactor]
    [shouts.shouts.presenter :as presenter]
    [ring.util.response]
    ))


(defn- render-index []
  (let [shouts (interactor/find_all)
        formatted_shouts (presenter/get_formatted_list_of_shouts shouts)]
    (render-template "shouts/index" :presenter formatted_shouts)))

(defn- render-show [key]
  (let [shout (interactor/find_by_key key)
        formatted_shout (presenter/get_formatted_shout shout)]
    (render-template "shouts/show" :presenter formatted_shout)))

(defn- render-new
  ([] (render-template "shouts/new"))
  ([shout user] 
   (let [created_shout (interactor/create shout user)]
     (redirect (str "/shouts/" (:key created_shout)))
   )
  )
)

(defroutes shouts-controller
           (GET "/shouts" [] (render-index))
           (GET "/shouts/new" [] (render-new))
           (POST "/shouts/new" {params :params} (render-new (params :shout) (params :user)))
           (GET "/shouts/:key" [key] (render-show key))
)
