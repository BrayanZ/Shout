(let [shout (:presenter *view-context*)]
    [:p "Shout: " (shout :shout) [:br]
        "by: " (shout :user) [:br]
        "on: " (shout :created_at)])

[:a {:href "/shouts"} "back"]
