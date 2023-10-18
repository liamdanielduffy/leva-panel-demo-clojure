(ns cljs-leva.core
  (:require
   [reagent.core :as reagent]
   [reagent.dom :as d]
   [leva.core :as leva]))

(defn leva-panel [!state-atom]
  [leva/SubPanel {:titleBar {:drag true}}
   [leva/Controls {:atom !state-atom}]])

(def selected-id (reagent/atom ""))

(defn select [new-id]
  (reset! selected-id new-id))

(defn selected [id]
  (= @selected-id id))

(defn toggle-leva-on-click []
  (reagent/with-let [!styles (reagent/atom {:background-color "orange"
                                            :height "100px"
                                            :width "100px"
                                            :border-color "black"
                                            :border-style "solid"
                                            :border-width "1px"})
                     id (str (random-uuid))]
    [:div
     [:div {:style @!styles :on-click #(select id)}]
     (when (selected id)
       (leva-panel !styles))]))

(defn mount-root []
  (d/render [:div [toggle-leva-on-click] [toggle-leva-on-click]] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
