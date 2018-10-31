
(ns app.main
  (:require [respo.core :refer [defcomp <> div span button]]
            [tiny-app.core :refer [create-app->]]))

(def store {:count 0, :states {}})

(defn updater [store op op-data]
  (case op
    :inc (update store :count inc)
    store))

(defcomp comp-container [store]
  (div {}
    (button {:inner-text "inc"
             :style {:border :none
                      :outline :none
                      :background-color "#aac"
                      :display :inline-block
                      :color :white
                      :border-radius "0px"
                      :cursor :pointer
                      :font-size 20}
             :on {:click (fn [e dispatch! mutate!]
                            (dispatch! :inc nil))}})
    (<> (:count store))))

(def app
  (create-app->
    {:model store
     :updater updater
     :view comp-container
     :mount-target (.querySelector js/document ".app")
     :ssr? false
     :show-ops? true}))

(def main! (:init! app))
(def reload! (:reload! app))
