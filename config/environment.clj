(use 'joodo.env)

(def environment {
  :joodo.core.namespace "shouts.core"
  ; environment settings go here
  })

(swap! *env* merge environment)