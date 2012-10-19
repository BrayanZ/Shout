(doctype :html5)
[:html
 [:head
  [:meta {:http-equiv "Content-Type" :content "text/html" :charset "iso-8859-1"}]
  [:title "shouts"]
  (include-css "/stylesheets/shouts.css")
  (include-js "/javascript/shouts.js")]
 [:body
  (eval (:template-body joodo.views/*view-context*))
]]