# tikcet-system

Il progetto presenta un piccolo strumento di gestione ticket. 
Permette di:
* Creare una nuova utenza, che sarà protetta attraverso utilizzo di token JWT 
  * Il jwt assegnerà all'utente uno tra 3 ruoli "Utente", "Supporto" e "Amministratore"
* Accedere alla homepage e visualizzare la lista dei ticket aperti
* Visualizzare i dettagli del singolo ticket e modificarli, qualora si avesse i privilegi
* I ticket sono gestiti attraverso un sistema ad eventi che ne modifica lo stato a fronte di determinate azioni
  * Stato TO_BE_ASSIGNED: Transizione ASSEGNAZIONE
  * Stato IN_PROGRESS: Transizione ACCETTAZIONE
  * Stato DENIED: Transizione RIFIUTO
  * Stato ON_HOLD: Transizione BLOCCO
  * Stato CLOSED: Transizione CONCLUSIONE
* Le modifiche agli stati sono pubblicate su Kafka dal TicketService e lette dall'Interceptor (consumer)
* Gli utenti sono notificati attraverso utilizzo di Websocket e STOMP

E' possibile eseguire il progetto dalla cartella root con il comando "docker-compose up", l'homepage sarà accessibile all'indirizzo localhost:80 

Maggiori dettagli relativi gli endpoint esposti dai servizi sono repribili a http://localhost:8080/swagger-ui/index.html#/


