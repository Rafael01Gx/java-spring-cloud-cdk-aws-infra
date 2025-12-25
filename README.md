# Infraestrutura Cloud com AWS CDK

> Repositório de estudos focado **exclusivamente na camada de infraestrutura** dentro de um ecossistema de microsserviços.

Este projeto demonstra, de forma prática e moderna, como **provisionar, versionar e operar infraestrutura na AWS** utilizando **Infrastructure as Code (IaC)** com **AWS CDK**.

---

## 🎯 Objetivo do Repositório

Este repositório faz parte de um **conjunto de estudos sobre arquitetura de microsserviços**, sendo responsável apenas pela **infraestrutura Cloud** necessária para suportar aplicações containerizadas em produção.

O foco está em:

* Automação de infraestrutura
* Padronização de ambientes
* Escalabilidade
* Observabilidade
* Boas práticas DevOps

---

## 🧠 Motivação

Levar a infraestrutura para a nuvem permite:

* Redução de custos operacionais
* Elasticidade sob demanda
* Alta disponibilidade
* Padronização de ambientes
* Deploys reprodutíveis e versionados

Este projeto explora **como sair do ambiente local (Docker)** e evoluir para uma **arquitetura Cloud-native na AWS**.

---

## 🏗️ Tecnologias e Serviços Utilizados

### Infrastructure as Code

* **AWS CDK (Cloud Development Kit)**
* Provisionamento via código
* Stacks versionadas

### Containers & Orquestração

* **Docker**
* **Amazon ECS**
* **AWS Fargate**
* **Amazon ECR**

### Banco de Dados

* **Amazon RDS (MySQL)**
* Banco gerenciado
* Integração segura com aplicações

### Observabilidade e Escala

* **Amazon CloudWatch** (logs e métricas)
* **Auto Scaling** de serviços

---

## 📦 Arquitetura Geral

* VPC dedicada
* Cluster ECS
* Serviços rodando em Fargate
* Imagens armazenadas no ECR
* Banco de dados no RDS
* Logs centralizados no CloudWatch

---

## 🚀 Conteúdo de Estudo

### 1️⃣ Preparando o Ambiente

* Conceitos de microsserviços
* Docker e containers
* Criação de imagens
* Dockerfile
* MySQL em container

---

### 2️⃣ Partindo para o Cloud

* Fundamentos de DevOps
* Infrastructure as Code
* Cloud Computing
* IAM Roles
* Bootstrapping da AWS
* Criação do primeiro projeto com CDK

---

### 3️⃣ Completando a Infraestrutura

* Conceito de Stacks
* Elastic Container Service (ECS)
* VPC e Networking
* Cluster e serviços
* Ordem correta de deploy
* Deploy completo das stacks

---

### 4️⃣ RDS – Relational Database Service

* Criação da instância de banco
* Integração da aplicação
* Configuração de credenciais
* Testes de conectividade

---

### 5️⃣ Verificação e Monitoramento

* Docker Hub vs ECR
* Logs no CloudWatch
* Monitoramento de serviços
* Auto Scaling

---

## 📚 Público-Alvo

* Desenvolvedores Backend
* Engenheiros de Software
* Engenheiros DevOps
* Arquitetos de Software

---

## ⚠️ Observações Importantes

* Projeto com **finalidade educacional**
* Custos na AWS podem ser gerados
* Recomenda-se uso de conta de estudos

---

## 🧭 Próximos Passos

* Integração com pipelines CI/CD
* Versionamento de ambientes
* Blue/Green Deploy
* Integração com serviços de segurança

---

## 📌 Status

🚧 Em evolução contínua como material de estudo em Cloud e DevOps.

---

### 📚  CDK Useful commands

 * `mvn package`     compile and run tests
 * `cdk ls`          list all stacks in the app
 * `cdk synth`       emits the synthesized CloudFormation template
 * `cdk deploy`      deploy this stack to your default AWS account/region
 * `cdk diff`        compare deployed stack with current state
 * `cdk docs`        open CDK documentation

