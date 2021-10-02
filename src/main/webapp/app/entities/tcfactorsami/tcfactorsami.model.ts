export interface ITCFACTORSAMI {
  idFactorSami?: number;
  minAsegurados?: string;
  maxAsegurados?: string;
  factor?: string;
}

export class TCFACTORSAMI implements ITCFACTORSAMI {
  constructor(public idFactorSami?: number, public minAsegurados?: string, public maxAsegurados?: string, public factor?: string) {}
}

export function getTCFACTORSAMIIdentifier(tCFACTORSAMI: ITCFACTORSAMI): number | undefined {
  return tCFACTORSAMI.idFactorSami;
}
