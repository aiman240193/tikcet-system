export const environment = {
    production: false,
    serverUrl: '/api',
    keycloak: {
      // Url of the Identity Provider
      issuer: 'http://localhost:8080/auth/',
      // Realm
      realm: 'ticket-system',
      clientId: 'ticket-system-client',
    },
  };