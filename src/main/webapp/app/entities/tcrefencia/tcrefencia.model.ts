export interface ITCREFENCIA {
  idReferencia?: number;
  periodo?: string;
  referencia?: string;
  edadpromedio?: string;
  cuota?: string;
}

export class TCREFENCIA implements ITCREFENCIA {
  constructor(
    public idReferencia?: number,
    public periodo?: string,
    public referencia?: string,
    public edadpromedio?: string,
    public cuota?: string
  ) {}
}

export function getTCREFENCIAIdentifier(tCREFENCIA: ITCREFENCIA): number | undefined {
  return tCREFENCIA.idReferencia;
}
