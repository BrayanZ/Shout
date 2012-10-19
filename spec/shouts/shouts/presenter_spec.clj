(ns shouts.shouts.presenter-spec
  (:use
    [speclj.core]
    [shouts.shouts.presenter :as presenter]
    [hyperion.api]
    [shouts.shouts.interactor :as interactor]
    ))

(set-ds! (new-datastore :implementation :memory))
(def test_shout (create "test shout!" "test user"))
(def created_shout1 (create "test shout 1" "user1"))
(def created_shout2 (create "test shout 2" "user2"))

(describe
  "presenter"
  (it
    "returns only the necesary attributes for the entity"
    (should= {:shout "test shout!" :user "test user" :key (:key test_shout) :created_at (.toString (:created-at test_shout))}
             (get_formatted_shout test_shout )))
  (it
    "returns a list of formatted entities"
    (should= (list
               {:shout "test shout 1"
                :user "user1"
                :key (:key created_shout1)
                :created_at (.toString (:created-at created_shout1))}

               {:shout "test shout 2"
                :user "user2"
                :key (:key created_shout2)
                :created_at (.toString (:created-at created_shout2))}
               )
             (get_formatted_list_of_shouts (list created_shout1 created_shout2))))
  )
