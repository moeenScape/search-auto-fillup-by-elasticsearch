## search-auto-fill-by-elasticsearch

### Steps to be followed in Elasticsearch + Kibana 
* Create a edge-ngram filter and edge-ngram analyzer on the index [products]
* Do a mapping of analyzer on top of that field on which you want to create tokens or wants auto suggests[name]
* Put some documents in the index created. [id,name,price,qty]
* Do an auto suggest search. [partial name]


### N-gram Search 
#### What is n-gram search?
Let's first discuss Full text search and Partial Search.
* Full-text search queries and performs linguistic searches against documents. It includes single or multiple words or phrases and returns documents that match search condition.Full text search is an extremely usefully tool for many application. <br> Example - Suppose you search Laptop on Google and get the result for this particular name so this is full text search
* another popular feature is partial word searching. Suppose you type some character of a work then it,s how some result.To search for partial worlds ,we can store variable-length sequence of a word in search engine.Another Word for a variable length sequence of a word is an n-gram.
Example : If we break up the word "sun" into n-grams ,we get : s,su,sun,u,un,n. Now if we store these n-grams in a search engine and associate them all to the word sun,we will able to find any documents containing the word sun by searching for any of these n-grams.
* ElasticSearch is a great search engine we can use to perform an n-gram search.

### What is Edge N-Gram?
It is a kind of N-gram where we make variable-length sequences of a word from start to the word like  
* Edge N-Gram : SUN = [s,su,sun]
* N-Gram : SUN =[s,su,sun,u,un,n]
The tokenizer first breaks text down into words whenever it encounters one of a list of specified characters,then it emits N-grams of each word where start of the N-gram is anchored to the beginning of the word.Example : sum =[s,su,sun]

When you want to search a text ,Edge N-grams have the advantage it can help you to autosuggestions the terms related to searched text.

### Create an edge-ngram filter and edge-ngram analyzer on the index  
```json
PUT product
{
  "settings": {
    "analysis": {
      "filter": {
        "ngram_filter": {
          "type": "edge_ngram",
          "min_gram": 1,
          "max_gram": 10
        }
      },
      "analyzer": {
        "ngram_analyzer": {
          "type": "custom",
          "tokenizer": "standard",
          "filter": ["lowercase", "ngram_filter"]
        }
      }
    }
  }
}
```
#### Mapping of analyzer on the top of that field on which we want to token or search

```json

PUT product/_mapping
{
  "properties" :{
    "name":{
      "type":"text",
      "analyzer":"ngram_analyzer"
    }
  }
}
```
#### Test Is analyzer Work 
```json
POST product/_analyze
{
  "analyzer" : "ngram_analyzer",
  "text" :"Moeen is a good boy"
}
```
#### Add value in index 
```json
PUT product/_doc/1
{
  "id" :1,
  "name":"Mobile",
  "type" : "mobile",
  "price":2000,
  "qty":10
}
```
#### View ALl Values in Product
```json
GET product/_search
```
#### Search product start with Mo 
```json
GET product/_search 
{
  "query": {
    "match": {
      "name": {
        "query": "Mo",
        "analyzer": "ngram_analyzer"
      }
    }
  }
}

```



