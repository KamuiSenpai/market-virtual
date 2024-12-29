export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api',
  endpoints: {
    login: '/usuarios/login',
    productos: '/productos',
    categorias: '/categorias',
    estados: '/estados',
    ordenes: '/ordenes',
    unidadesMedida: '/unidades-medida',
    historialOrdenes: '/ordenes/{ordenId}/historial-estados'
  }
};
