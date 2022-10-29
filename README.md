# Manual de Execução

---
### 1. Requisitos
Conter git instalando na máquina;

Conter docker-compose instalado na máquina.

---
<br/>

### 2. Realizando deploy local da aplicação
Passo 1:
```
Clone o repositório utilizando o comando:
git clone https://github.com/pedroap94/desafio2.git
```

Passo 2:
```
Acesso localmente o diretório raiz da aplicação através do terminal
```

Passo 3:
```
rode o comando:
docker-compose up -d
```
---
Após as mensagens de "done" do docker-compose indicando que os containers
estão ativos, a aplicação estará disponível em:
```
http://localhost:9000/
```

Os logs podem ser lidos diretamente no terminal através do comando:
```
docker logs desafio2
```

---
<br/>

### 3. Endpoints
Esta aplicação utiliza Swagger 2.0, portanto a documentação dos endpoints
encontra-se em:
```
http://localhost:9000/swagger-ui/
```

Para facilitar os testes, abaixo está o link para download da coleção do Postman:
```
https://drive.google.com/file/d/1bjZrlRRLDAectVeFZZHHyQpOkyRwdUsy/view?usp=sharing
```

---
<br/>

### 4. Desligar a aplicação
Para finalizar a aplicação use o comando:
```
docker-compose down
```