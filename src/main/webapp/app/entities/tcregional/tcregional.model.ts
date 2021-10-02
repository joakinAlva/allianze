export interface ITCREGIONAL {
  idRegional?: number;
  regional?: string;
}

export class TCREGIONAL implements ITCREGIONAL {
  constructor(public idRegional?: number, public regional?: string) {}
}

export function getTCREGIONALIdentifier(tCREGIONAL: ITCREGIONAL): number | undefined {
  return tCREGIONAL.idRegional;
}
