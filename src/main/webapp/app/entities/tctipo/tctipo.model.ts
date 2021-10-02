export interface ITCTIPO {
  idTipo?: number;
  tipoRegla?: string;
}

export class TCTIPO implements ITCTIPO {
  constructor(public idTipo?: number, public tipoRegla?: string) {}
}

export function getTCTIPOIdentifier(tCTIPO: ITCTIPO): number | undefined {
  return tCTIPO.idTipo;
}
