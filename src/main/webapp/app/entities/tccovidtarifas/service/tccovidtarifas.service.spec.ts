import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCCOVIDTARIFAS, TCCOVIDTARIFAS } from '../tccovidtarifas.model';

import { TCCOVIDTARIFASService } from './tccovidtarifas.service';

describe('Service Tests', () => {
  describe('TCCOVIDTARIFAS Service', () => {
    let service: TCCOVIDTARIFASService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCCOVIDTARIFAS;
    let expectedResult: ITCCOVIDTARIFAS | ITCCOVIDTARIFAS[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCCOVIDTARIFASService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idCovidTarifas: 0,
        edad: 'AAAAAAA',
        qxcnsfg: 0,
        titular: 'AAAAAAA',
        conyuge: 'AAAAAAA',
        hijoMayor: 'AAAAAAA',
        hijoMenor: 'AAAAAAA',
        qxTitular: 0,
        qxConyuge: 0,
        qxHijoMayor: 0,
        qxHijoMenor: 0,
        qxTitularRecargada: 0,
        qxConyugeRecargada: 0,
        qxHijoMayorRecargada: 0,
        qxHijoMenorRecargada: 0,
        valorGff: 0,
        valorGffCovid: 0,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TCCOVIDTARIFAS', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCCOVIDTARIFAS()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCCOVIDTARIFAS', () => {
        const returnedFromService = Object.assign(
          {
            idCovidTarifas: 1,
            edad: 'BBBBBB',
            qxcnsfg: 1,
            titular: 'BBBBBB',
            conyuge: 'BBBBBB',
            hijoMayor: 'BBBBBB',
            hijoMenor: 'BBBBBB',
            qxTitular: 1,
            qxConyuge: 1,
            qxHijoMayor: 1,
            qxHijoMenor: 1,
            qxTitularRecargada: 1,
            qxConyugeRecargada: 1,
            qxHijoMayorRecargada: 1,
            qxHijoMenorRecargada: 1,
            valorGff: 1,
            valorGffCovid: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCCOVIDTARIFAS', () => {
        const patchObject = Object.assign(
          {
            edad: 'BBBBBB',
            qxcnsfg: 1,
            conyuge: 'BBBBBB',
            hijoMayor: 'BBBBBB',
            qxConyugeRecargada: 1,
            qxHijoMenorRecargada: 1,
            valorGff: 1,
          },
          new TCCOVIDTARIFAS()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCCOVIDTARIFAS', () => {
        const returnedFromService = Object.assign(
          {
            idCovidTarifas: 1,
            edad: 'BBBBBB',
            qxcnsfg: 1,
            titular: 'BBBBBB',
            conyuge: 'BBBBBB',
            hijoMayor: 'BBBBBB',
            hijoMenor: 'BBBBBB',
            qxTitular: 1,
            qxConyuge: 1,
            qxHijoMayor: 1,
            qxHijoMenor: 1,
            qxTitularRecargada: 1,
            qxConyugeRecargada: 1,
            qxHijoMayorRecargada: 1,
            qxHijoMenorRecargada: 1,
            valorGff: 1,
            valorGffCovid: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TCCOVIDTARIFAS', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCCOVIDTARIFASToCollectionIfMissing', () => {
        it('should add a TCCOVIDTARIFAS to an empty array', () => {
          const tCCOVIDTARIFAS: ITCCOVIDTARIFAS = { idCovidTarifas: 123 };
          expectedResult = service.addTCCOVIDTARIFASToCollectionIfMissing([], tCCOVIDTARIFAS);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCOVIDTARIFAS);
        });

        it('should not add a TCCOVIDTARIFAS to an array that contains it', () => {
          const tCCOVIDTARIFAS: ITCCOVIDTARIFAS = { idCovidTarifas: 123 };
          const tCCOVIDTARIFASCollection: ITCCOVIDTARIFAS[] = [
            {
              ...tCCOVIDTARIFAS,
            },
            { idCovidTarifas: 456 },
          ];
          expectedResult = service.addTCCOVIDTARIFASToCollectionIfMissing(tCCOVIDTARIFASCollection, tCCOVIDTARIFAS);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCCOVIDTARIFAS to an array that doesn't contain it", () => {
          const tCCOVIDTARIFAS: ITCCOVIDTARIFAS = { idCovidTarifas: 123 };
          const tCCOVIDTARIFASCollection: ITCCOVIDTARIFAS[] = [{ idCovidTarifas: 456 }];
          expectedResult = service.addTCCOVIDTARIFASToCollectionIfMissing(tCCOVIDTARIFASCollection, tCCOVIDTARIFAS);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCOVIDTARIFAS);
        });

        it('should add only unique TCCOVIDTARIFAS to an array', () => {
          const tCCOVIDTARIFASArray: ITCCOVIDTARIFAS[] = [{ idCovidTarifas: 123 }, { idCovidTarifas: 456 }, { idCovidTarifas: 25699 }];
          const tCCOVIDTARIFASCollection: ITCCOVIDTARIFAS[] = [{ idCovidTarifas: 123 }];
          expectedResult = service.addTCCOVIDTARIFASToCollectionIfMissing(tCCOVIDTARIFASCollection, ...tCCOVIDTARIFASArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCCOVIDTARIFAS: ITCCOVIDTARIFAS = { idCovidTarifas: 123 };
          const tCCOVIDTARIFAS2: ITCCOVIDTARIFAS = { idCovidTarifas: 456 };
          expectedResult = service.addTCCOVIDTARIFASToCollectionIfMissing([], tCCOVIDTARIFAS, tCCOVIDTARIFAS2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCOVIDTARIFAS);
          expect(expectedResult).toContain(tCCOVIDTARIFAS2);
        });

        it('should accept null and undefined values', () => {
          const tCCOVIDTARIFAS: ITCCOVIDTARIFAS = { idCovidTarifas: 123 };
          expectedResult = service.addTCCOVIDTARIFASToCollectionIfMissing([], null, tCCOVIDTARIFAS, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCOVIDTARIFAS);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
