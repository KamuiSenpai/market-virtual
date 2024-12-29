export const environment = {
  production: true,
  apiUrl: 'http://localhost:8080/api', // URL base de tu backend
  endpoints: {
    login: '/usuarios/login', // UsuarioController: Login de usuarios
    productos: '/productos', // ProductoController: Gestión de productos
    categorias: '/categorias', // CategoriaController: Gestión de categorías
    estados: '/estados', // OrdenEstadoController: Gestión de estados de órdenes
    ordenes: '/ordenes', // OrdenController: Gestión de órdenes
    unidadesMedida: '/unidades-medida', // UnidadMedidaController: Gestión de unidades de medida
    historialOrdenes: '/ordenes/{ordenId}/historial-estados' // OrdenesEstadoHistorialController: Historial de estados
  }
};
