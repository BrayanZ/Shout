(ns shouts.controller.shouts-controller-spec
  (:use 
    [speclj.core]
    [joodo.spec-helpers.controller]
    [shouts.controller.shouts-controller]
    [hyperion.api]
    [shouts.shouts.interactor]))

(set-ds! (new-datastore :implementation :memory))

(describe
  "shouts-controller"
  (with-mock-rendering)
  (with-routes shouts-controller)

  (it "redirects to the shouts page"
      (let [result (do-get "/shouts")]
        (should= 200 (:status result))
        (should= "shouts/index" @rendered-template)))

  (it "redirects to the show shout page"
      (let [shout (create "test shout" "user")
            result (do-get (str "/shouts/" (:key shout)))]
        (should= 200 (:status result))
        (should= "shouts/show" @rendered-template)))

  (it "redirects to the new page"
      (let [result (do-get "/shouts/new")]
        (should= 200 (:status result))
        (should= "shouts/new" @rendered-template )
      )
  )

  (it "redirects to the show page after adding new shout"
      (let [result (do-post "/shouts/new" :params {:shout "nuevo" :user "user"} )]
        (should= 302 (:status result))
      )
  )

)

