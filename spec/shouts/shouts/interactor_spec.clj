(ns shouts.shouts.interactor-spec
  (:use
    [speclj.core]
    [shouts.shouts.interactor :as shout]
    [hyperion.api]))

(set-ds! (new-datastore :implementation :memory))

(def test_shout {:shout "test shout!" :user "Test Creator"})

(describe 
  "interactor"
  (it
    "creates a new shout"
    (def saved_shout (create (:shout test_shout) (:user test_shout)))
    (should= "test shout!" (:shout saved_shout))
    (should= "Test Creator" (:user saved_shout))
    (should-not=  nil (find saved_shout :key)))

  (it
    "finds a shout by key"
    (def created_shout (create (:shout test_shout) (:user test_shout)))
    (def founded_shout (find_by_key (:key created_shout)))
    (should= "test shout!" (:shout founded_shout))
    (should= "Test Creator" (:user founded_shout)))

  (it
    "finds all the shoutes"
    (delete-by-kind :shout)
    (def created_shout1 (create "test shout 1" "user1"))
    (def created_shout2 (create "test shout 2" "user2"))
    (def created_shout3 (create "test shout 3" "user3"))
    (def result (find_all))
    (should= 3 (count result)))

  )
