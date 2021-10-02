import * as dayjs from 'dayjs';
import { ITCPERFIL } from 'app/entities/tcperfil/tcperfil.model';

export interface ITMUSUARIO {
  idUsuario?: number;
  nombre?: string;
  apellidos?: string;
  correoElectronico?: string;
  usuario?: string;
  clave?: string;
  fechaRegistro?: dayjs.Dayjs;
  token?: string;
  estatus?: number;
  employee?: ITCPERFIL | null;
}

export class TMUSUARIO implements ITMUSUARIO {
  constructor(
    public idUsuario?: number,
    public nombre?: string,
    public apellidos?: string,
    public correoElectronico?: string,
    public usuario?: string,
    public clave?: string,
    public fechaRegistro?: dayjs.Dayjs,
    public token?: string,
    public estatus?: number,
    public employee?: ITCPERFIL | null
  ) {}
}

export function getTMUSUARIOIdentifier(tMUSUARIO: ITMUSUARIO): number | undefined {
  return tMUSUARIO.idUsuario;
}
