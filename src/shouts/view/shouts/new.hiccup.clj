(form-to [:post "/shouts/new"]
         (label "shout" "Shout: ")
         (text-area "shout")

         (label "user" "User: ")
         (text-field "user")

         (submit-button "Save")
         )

[:a {:href "/shouts"} "Back"]
