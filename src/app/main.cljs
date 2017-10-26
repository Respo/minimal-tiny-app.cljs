
(ns app.main
  (:require [respo.macros :refer [defcomp <> div span button]]
            [tiny-app.core :refer [create-tiny-app->]]))

; need to require respo.core and respo.cursor since code in Macros need them

(def store {:count 0, :states 0})

(defn updater [store op op-data]
  (case op
    :inc (update store :count inc)
    nil))

(def style-button
  {:border :none
   :outline :none
   :background-color "#aac"
   :display :inline-block
   :color :white
   :border-radius "0px"
   :cursor :pointer
   :font-size 20})

(defn on-inc [e dispatch! mutate!]
  (dispatch! :inc nil))

(defcomp comp-container [store]
  (div {}
    (button {:on {:click on-inc}
             :inner-text "inc"
             :style style-button})
    (<> (:count store))))

(def app
  (create-tiny-app->
    {:model store
     :updater updater
     :view comp-container
     :mount-target (.querySelector js/document ".app")
     :ssr? false
     :show-ops? true}))

(def reload! (:reload! app))

(set! (.-onload js/window) (:start-app! app))
