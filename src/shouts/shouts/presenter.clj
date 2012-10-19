(ns shouts.shouts.presenter)
(import java.util.Date)

(defn get_formatted_shout [shout]
  {:shout (:shout shout)
   :user (:user shout)
   :key (:key shout)
   :created_at (.toString (let [created-date (:created-at shout)]
                            (if
                              (= Date (class created-date))
                              created-date
                              (new Date created-date))))
   })

(defn get_formatted_list_of_shouts [shouts]
  (map get_formatted_shout shouts))
