export interface ITCCOBERTURA {
  idCobertura?: number;
  cobertura?: string;
  posicion?: number;
}

export class TCCOBERTURA implements ITCCOBERTURA {
  constructor(public idCobertura?: number, public cobertura?: string, public posicion?: number) {}
}

export function getTCCOBERTURAIdentifier(tCCOBERTURA: ITCCOBERTURA): number | undefined {
  return tCCOBERTURA.idCobertura;
}
