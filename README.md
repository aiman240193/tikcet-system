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

Di seguito è spiegato il funzionamento della webapp

Inizialmente si atterrerà sulla homepage con la sola possibilità di fare login e signup
![image](https://github.com/aiman240193/tikcet-system/assets/39050299/8f20f23a-40d7-407b-9928-d28135f51e33)
Sscegliendo i tasti login e signup appariranno delle form
![image](https://github.com/aiman240193/tikcet-system/assets/39050299/42e32acd-3fc7-41b6-9e35-dd6d2b87d075)
Una volta effettuato il login verrà staccato il token jwt contenente il ruolo dell'utente, che sarà mostrato a video, a seconda del ruolo posseduto appariranno differenti pulsanti all'interno della sidebar.
![image](https://github.com/aiman240193/tikcet-system/assets/39050299/6c4db7cf-577c-47d2-b642-9ad6ba42e406)
Cliccando al percorso ticket si accederà ad una schermata contenente una turbotable che offre la possibilità di effettuare operazioni crud.
![image](https://github.com/aiman240193/tikcet-system/assets/39050299/a152fb28-c994-4bd9-b30d-4166548dfcc4)
Se si crea un nuovo ticket questo sarà creato senza nessun assegnatario e allo stato to be assigned
![image](https://github.com/aiman240193/tikcet-system/assets/39050299/009a9b0e-5907-42eb-89e9-b951997a4d66)
Entrando nella pagina relativa i dettagli del ticket è possibile nella casella "Actions" assegnare il ticket, accettarlo, rifiutarlo, mandarlo in esecuzione e svolgere altre azioni
![image](https://github.com/aiman240193/tikcet-system/assets/39050299/903eeb99-9bd5-4b77-a491-a0d4445f5e7e)
Ogni volta che è svolta un'azione un evento Kafka a backend avvierà l'invio di messaggi websocket che saranno recapitati e visualizzati in real time sul client direttamente sotto la toolbar. Con il pulsante clear è possibile pulire i messaggi.
![image](https://github.com/aiman240193/tikcet-system/assets/39050299/77eacef7-8f57-47b8-86b9-e49d03e6bd97)




