export class User {
  email!: string;
  password!: string;
  token?: string; // Opcional, para almacenar el JWT
}
