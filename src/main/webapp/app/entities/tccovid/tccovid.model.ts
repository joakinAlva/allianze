export interface ITCCOVID {
  idCovid?: number;
  edad?: string;
  basica?: number;
  recargoEdad?: number;
  recargoGiro?: number;
  recargoTotal?: number;
  basicaRecargada?: number;
}

export class TCCOVID implements ITCCOVID {
  constructor(
    public idCovid?: number,
    public edad?: string,
    public basica?: number,
    public recargoEdad?: number,
    public recargoGiro?: number,
    public recargoTotal?: number,
    public basicaRecargada?: number
  ) {}
}

export function getTCCOVIDIdentifier(tCCOVID: ITCCOVID): number | undefined {
  return tCCOVID.idCovid;
}
