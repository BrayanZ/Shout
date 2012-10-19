[:h1 "Shout, let it all out"]
(let [shouts (:presenter *view-context*)]
  (for [shout shouts]
    [:p "Shout: " (shout :shout) [:br]
        "by: " (shout :user) [:br]
        "on: " (shout :created_at)]))

[:a {:href "/shouts/new"} "New shout"]
