export interface ITCSUMAASEGURADA {
  idSumaAsegurada?: number;
  sagff?: number;
  saca?: number;
  sage?: number;
  saiqa?: number;
  saiqv?: number;
}

export class TCSUMAASEGURADA implements ITCSUMAASEGURADA {
  constructor(
    public idSumaAsegurada?: number,
    public sagff?: number,
    public saca?: number,
    public sage?: number,
    public saiqa?: number,
    public saiqv?: number
  ) {}
}

export function getTCSUMAASEGURADAIdentifier(tCSUMAASEGURADA: ITCSUMAASEGURADA): number | undefined {
  return tCSUMAASEGURADA.idSumaAsegurada;
}
