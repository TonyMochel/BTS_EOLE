# Projet EOLE

## Contexte
Afin de dynamiser le secteur de la location de voiliers, le directeur de la Sté EOLE, M.Sirocco organise régulièrement des régates. C’est donc tout naturellement que M. Sirocco a confié à notre société l’informatisation du déroulement des régates. Le projet sera à développer en Java avec le framework Swing. M. Sirocco attend de vous une solution simple, pas chère, et évolutive.

Une régate consiste à effectuer un parcours d'une certaine distance (en milles) dans le meilleur temps possible. La distance est bien sûr connue avant le début de la course. Avant chaque régate, on enregistre les noms des voiliers participants (au maximum 20), leur classe (1, 2, 3 ou 4), leur rating ainsi que le nom du skipper. Le rating est défini par un organisme international (jauge IOR) ; c'est une valeur spécifique à chaque voilier qui a pour but de donner les mêmes chances à des voiliers ayant des potentiels différents.
Les classements se font par classe. Tous les voiliers partent ensemble, mais un voilier se mesure seulement aux autres voiliers de la même classe. On aura donc un classement spécifique pour les bateaux de la classe 1, un classement spécifique pour les bateaux de la classe 2, etc.

## Développement

Après avoir conceptualisé et développer une première version de l'application pour le ppe. J'ai repris, par ma propre initiative, l'intégralité de l'application pour parfaire mes compétences en Java et en architecture (MVC, DAO, Factory, ...).

J'ai donc développé une nouvelle version de l'application en utilisant le pattern MVC (Model Vue Contrôleur) et une couche DAO pour améliorer l'architecture de l'application. L'application est devenue bien plus maintenable et évolutive que la première (cf: demande faite par M.Sirocco). Il sera beaucoup plus simple de l'adapter pour une évolution future.

## Images
[Création d'une course](https://drive.google.com/open?id=1MUVKLZrWlKZ6KgHoYo4o1OvRd0JJFSTJ)
[Inscription des participants](https://drive.google.com/open?id=176FsTJDL-zc7Wm40_wN9-dOFmNrMp92K)
[Panneau de contrôle](https://drive.google.com/open?id=1zh6jpkDqEwGL6AuoBgrayOlFyYFws8BT)
[Classement](https://drive.google.com/open?id=1es1kVIOKWMQKf1BBory4X29zwc8rdiIW)

## Ressources
* [Modélisation de la base de données](https://drive.google.com/open?id=1Sm6DFfqCmYnIvOnxlTrok9--MKLKGnSx) 
* [Exemple d'un fichier d'impression](https://drive.google.com/open?id=1o1AlnqLkJxtiQSsiWRtPWB0IcFnkPmZ4)
* [Sujet du projet](https://drive.google.com/open?id=1X8Q-sKd5qHxdOaIT29RKydVTjCNtWXSe)

## Technologies :
* Langage : Java
* Framework : Swing
* Librairie : JDatepicker
* Pattern : MVC et DAO
* Documentation : JavaDoc
