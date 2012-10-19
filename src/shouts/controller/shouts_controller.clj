(ns shouts.controller.shouts-controller
  (:use
    [compojure.core :only (defroutes GET POST context)]
    [compojure.route :only (not-found)]
    [joodo.middleware.view-context :only (wrap-view-context)]
    [joodo.views :only (render-template render-html)]
    [joodo.controllers :only (controller-router)]
    [shouts.shouts.interactor :as interactor]
    [shouts.shouts.presenter :as presenter]
    [ring.util.response]
    ))


(defn- do-index []
  (let [shouts (interactor/find_all)
        formatted_shouts (presenter/get_formatted_list_of_shouts shouts)]
    (render-template "shouts/index" :presenter formatted_shouts)))

(defn- do-show [key]
  (let [shout (interactor/find_by_key key)
        formatted_shout (presenter/get_formatted_shout shout)]
    (render-template "shouts/show" :presenter formatted_shout)))

(defn- do-new
  ([] (render-template "shouts/new"))
  ([shout user] 
   (let [created_shout (interactor/create shout user)]
     (redirect (str "/shouts/" (:key created_shout)))
   )
  )
)

(defroutes shouts-controller
           (GET "/shouts" [] (do-index))
           (context "/shouts" []
                    (GET "/new" [] (do-new))
                    (POST "/new" {params :params} (do-new (params :shout) (params :user)))
                    (GET "/:key" [key] (do-show key)))
)
