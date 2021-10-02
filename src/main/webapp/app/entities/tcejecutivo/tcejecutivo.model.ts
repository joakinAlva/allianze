export interface ITCEJECUTIVO {
  idEjecutivo?: number;
  ejecutivo?: string;
}

export class TCEJECUTIVO implements ITCEJECUTIVO {
  constructor(public idEjecutivo?: number, public ejecutivo?: string) {}
}

export function getTCEJECUTIVOIdentifier(tCEJECUTIVO: ITCEJECUTIVO): number | undefined {
  return tCEJECUTIVO.idEjecutivo;
}
