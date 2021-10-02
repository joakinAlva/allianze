export interface ITCHIPOTESIS {
  idHipotesis?: number;
  hipotesis?: string;
  valor?: number;
  variable?: string;
}

export class TCHIPOTESIS implements ITCHIPOTESIS {
  constructor(public idHipotesis?: number, public hipotesis?: string, public valor?: number, public variable?: string) {}
}

export function getTCHIPOTESISIdentifier(tCHIPOTESIS: ITCHIPOTESIS): number | undefined {
  return tCHIPOTESIS.idHipotesis;
}
