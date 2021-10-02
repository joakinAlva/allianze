import { ITMUSUARIO } from 'app/entities/tmusuario/tmusuario.model';

export interface ITCPERFIL {
  idPerfil?: number;
  perfil?: string;
  tMUSUARIOS?: ITMUSUARIO[] | null;
}

export class TCPERFIL implements ITCPERFIL {
  constructor(public idPerfil?: number, public perfil?: string, public tMUSUARIOS?: ITMUSUARIO[] | null) {}
}

export function getTCPERFILIdentifier(tCPERFIL: ITCPERFIL): number | undefined {
  return tCPERFIL.idPerfil;
}
