# CV Management Application

## Overview  
Cette application modulaire gère le cycle complet des CV : création, mise à jour, archivage, suppression, ainsi que la gestion des carnets d’adresses, références et un service d'appariement CV-mission basé sur un algorithme simple de machine learning.  
L’architecture est basée sur des microservices développés avec Spring Boot, Spring Cloud, et technologies associées.

---

## Objectives

- **Gestion du cycle de vie des CV** : opérations CRUD complètes.  
- **Gestion du carnet d’adresses** : contacts et références liés aux CV.  
- **Appariement CV-Mission** : service de matching utilisant un score basé sur les mots-clés (ex : TF-IDF).  
- **Interface sécurisée** : API sécurisée avec authentification JWT, testée via Postman.

---

## Technologies Utilisées

| Technologie       | Description                                          |
|-------------------|------------------------------------------------------|
| Spring Boot       | Développement des API REST et logique métier         |
| Spring Cloud      | Gestion des microservices (Eureka, API Gateway)      |
| Microservices     | Architecture découplée par modules fonctionnels      |
| PostgreSQL        | Persistance des données CV, utilisateurs, références |
| Postman           | Tests et validation des API                          |
| Machine Learning  | Scoring simple TF-IDF pour matching CV-mission       |
| UML               | Modélisation des cas d’usage, classes, séquences     |
| Docker            | Conteneurisation et déploiement                      |
| Resilience4J      | Tolérance aux pannes des microservices               |
| OpenFeign         | Communication REST simplifiée entre services         |
| JUnit             | Tests unitaires et d’intégration                     |

---

## Structure du Projet

```

cv-management/
├── eureka-server/        # Service de découverte Eureka
├── api-gateway/          # Passerelle API pour le routage
├── customer-service/     # Gestion des utilisateurs (Admin, Candidat, Recruteur)
├── cv-service/           # Gestion du cycle de vie des CV
├── reference-service/    # Gestion des références
├── mission-service/    # Gestion des missions
├── matching-service/     # Service d’appariement CV-Mission avec scoring
├── ml-service/          # Modéle Flask
├── pom.xml               # POM parent Maven
├── README.md             # Documentation du projet

````

---

## Architecture Microservices

| Service            | Responsabilité                                     |
|--------------------|---------------------------------------------------|
| Customer Service    | Gestion des utilisateurs et rôles                  |
| CV Service         | Gestion création, mise à jour, archivage, suppression des CV |
| Reference Service  | Gestion des références liées aux CV                |
| Matching Service   | Apparier CV et missions via un algorithme de scoring |
| Eureka Server      | Découverte des microservices                        |
| API Gateway       | Point d’entrée centralisé pour le routage          |

---

## Instructions d’Installation

### Prérequis

- Java 17  
- Maven  
- Docker  
- PostgreSQL 
- Postman (pour tests API)  
- Git  

### Installation

```bash
# Cloner le dépôt
git clone <repository-url>
cd cv-management

# Compiler le projet
mvn clean install

# Lancer les conteneurs Docker
docker-compose up -d --build
````

### Vérification

```bash
# Suivre les logs des conteneurs
docker-compose logs -f
```

### Accès aux services

* Eureka Server : [http://localhost:8761](http://localhost:8761)
* API Gateway : [http://localhost:8888](http://localhost:8888)
* Exemple endpoint : [http://localhost:8888/CUSTOMER-SERVICE/api/customers](http://localhost:8888/CUSTOMER-SERVICE/api/auth)

---

## Configuration

### Eureka Server

Fichier : `eureka-server/src/main/resources/application.yml`

```yaml
server:
  port: 8761
eureka:
  client:
    registerWithEureka: false
    fetchRegistry: false
```

### API Gateway

Fichier : `api-gateway/src/main/resources/application.yml`

```yaml
spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
server:
  port: 8888
```

---

## Test des API

* Utiliser Postman pour tester les endpoints.
* Organiser les requêtes dans une collection Postman, exportable en JSON pour archivage.
* Endpoints clés :

  * Customer Service : CRUD utilisateurs (`/api/auth`)
  * CV Service : CRUD CV + archivage (`/api/cvs`, `/api/cvs/{id}/archiver`)
  * Reference Service : CRUD références
  * Matching Service : calcul des scores CV-Mission
  * Authentification : login JWT et accès sécurisé

---

## Diagrammes UML

* **Use Case Diagram** : interactions des acteurs (Admin, Candidat, Recruteur).
* **Class Diagram** : entités principales (User, CV, Reference, Mission) et relations.
* **Sequence Diagram** : scénarios clés (création et archivage de CV).
* **Component Diagram** : microservices et leurs interactions.

---

## Développement & Tests

### Développement

* Microservices indépendants, chacun avec son CRUD.
* Gestion des rôles et sécurité JWT.
* Implémentation du scoring TF-IDF simple pour le matching.
* Tolérance aux pannes via Resilience4J.

### Tests

* **Unitaires** : JUnit (ex. `CVRepositoryTest`, `CustomerControllerTest`).
* **Intégration** : tests de cycles complets (création, mise à jour, archivage, suppression).
* **Postman** : validation des API REST via collection.

---

## Déploiement

### Exemple Dockerfile (cv-service)

```dockerfile
FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/cv-service.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Orchestration Docker Compose

```bash
docker-compose build
docker-compose up -d
```

---

## Perspectives d’Évolution

* Amélioration de l’algorithme ML pour un matching plus précis.
* Mise en place de pipelines CI/CD pour tests et déploiements automatisés.
* Ajout d’outils de monitoring (Prometheus, Grafana).
* Renforcement de la sécurité avec OAuth2 ou JWT avancé.

---

## Annexes

* `pom.xml` : POM parent multi-modules.
* `docker-compose.yml` : orchestration des services.
* Collection Postman exportée (JSON).
* Captures d’écran : IDE, tests Postman, logs conteneurs.

---

