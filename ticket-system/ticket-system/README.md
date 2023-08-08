# Ticket Support System
Il progetto presenta un piccolo strumento di gestione ticket. 
Permette di:
* Creare una nuova utenza, che sarà protetta attraverso utilizzo di token JWT e keycloak
* Sono presenti 2 ruoli "Utente" e "Amministratore"
* Accedere alla homepage e visualizzare la lista dei ticket aperti
* Visualizzare i dettagli del singolo ticket e modificarli, qualora si avesse i privilegi
* I ticket sono gestiti attraverso una state machine che ne modifica lo stato a fronte di determinati eventi
  * Stato TO_BE_ASSIGNED: Transizione ASSEGNAZIONE
  * Stato IN_PROGRESS: Transizione ACCETTAZIONE
  * Stato DENIED: Transizione RIFIUTO
  * Stato ON_HOLD: Transizione BLOCCO
  * Stato CLOSED: Transizione CONCLUSIONE
* Le modifiche agli stati sono pubblicate su Kafka dal TicketService e lette dall'Interceptor (consumer)
* Gli utenti sono notificati attraverso utilizzo di Websocket e STOMP

Maggiori dettagli relativi gli endpoint esposti dai servizi sono reprivili a ...LINK SWAGGER...

Per accedere al servizio è possibile creare un account al link ...ISTANZA DI AWS...

è possibile clonare il repository e mandarlo in esecuzione con ...COMANDO DOCKER...

Tracciatura e monitoraggio con Elastick e Kibana al link ...LINK KIBANA...

TODO:
-Creare pagina profile
-Sistemare gestione stati ticket
-Aggiungere created by alla creazione
-Aggiungere campi createdby e assignedto alla scheda dal ticket
-Emettere e intercettare eventi kafka
-Websocket
-Aggiungere all'app una scheda "Notifiche" a cui arrivano le notifiche dei websocket
-Popolare statistiche di chiusura
  -Aggiungere close date al ticket
  -Raccogliere le statistiche onInit nella lista ticket