export interface ITCCONCEPTO {
  idConcepto?: number;
  concepto?: string;
  dato?: number;
}

export class TCCONCEPTO implements ITCCONCEPTO {
  constructor(public idConcepto?: number, public concepto?: string, public dato?: number) {}
}

export function getTCCONCEPTOIdentifier(tCCONCEPTO: ITCCONCEPTO): number | undefined {
  return tCCONCEPTO.idConcepto;
}
