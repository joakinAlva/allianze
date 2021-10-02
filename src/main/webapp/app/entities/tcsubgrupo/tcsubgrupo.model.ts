export interface ITCSUBGRUPO {
  idSubGrupo?: number;
  subGrupo?: string;
}

export class TCSUBGRUPO implements ITCSUBGRUPO {
  constructor(public idSubGrupo?: number, public subGrupo?: string) {}
}

export function getTCSUBGRUPOIdentifier(tCSUBGRUPO: ITCSUBGRUPO): number | undefined {
  return tCSUBGRUPO.idSubGrupo;
}
