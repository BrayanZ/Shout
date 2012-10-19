(ns shouts.shouts.interactor
  (:use 
    [hyperion.api]))

(defentity Shout
           [shout]
           [user]
           [created_at]
           [updated_at])

(defn create [shout_message user]
  (let [shout (shout :shout shout_message :user user)]
    (save shout)))

(defn find_by_key [key]
  (find-by-key key))

(defn find_all []
  (find-by-kind :shout ))
