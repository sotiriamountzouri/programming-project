# programming-project


## Περιγραφή
Η παρούσα εργασία αφορά την ανάπτυξη μιας εφαρμογής που επιλύει προβλήματα βελτιστοποίησης διαδρομών για κρουαζιερόπλοια , εξασφαλίζοντας τους την ορθή σείρα που πρέπει να ακολουθήσουν μεταξύ των επιλεγμένων νησιών.

---

## Οδηγίες Μεταγλώττισης
Για να μεταγλωττίσετε το πρόγραμμα, εκτελέστε την παρακάτω εντολή στον φάκελο του έργου (πλοηγηθείτε αρχικά στον φακελο `shipdis`):

```bash
mvn compile
```

Για τη δημιουργία του εκτελέσιμου JAR:

```bash
mvn package
```

---

## Οδηγίες Εκτέλεσης
Για να εκτελέσετε το πρόγραμμα, χρησιμοποιήστε την παρακάτω εντολή:

```bash
java -jar target/shipdis-0.1.jar
```

Καθώς το εκτελέσιμο αρχείο βρίσκεται μέσα στο φάκελο `target`, βεβαιωθείτε ότι εκτελείτε την εντολή από τον κατάλληλο φάκελο.

---

## Οδηγίες Χρήσης
Ακολουθήστε τις παρακάτω οδηγίες για τη σωστή χρήση της εφαρμογής.

## Οδηγίες
1. **Αφετηρία**:  
   Στο πεδίο αφετηρίας καταχωρείτε την αφετηρία (π.χ. Πειραιάς ή καποιο νησί).

2. **Προσθήκη Προορισμού**:  
   - Στο πεδίο "Προσθήκη Προορισμού", εισάγετε τα νησιά  (π.χ. Σαντορίνη, Νάξος), ένα κάθε φορά.
   - Πατήστε το κουμπί **Προσθήκη** για να καταχωρηθεί κάθε προορισμός.

3. **Καθαρισμός Λίστας**:  
   - Το κουμπί **Καθαρισμός Λίστας** διαγράφει όλα τα επιλεγμένα νησιά από τη λίστα.

4. **Υποβολή Δεδομένων**:  
   - Πατήστε **Υποβολή Δεδομένων** για να εμφανιστούν τα αποτελέσματα.

## Σημαντικές Παρατηρήσεις
- Τα δεδομένα πρέπει να υποβάλλονται **με ελληνικούς χαρακτήρες**.
- Τα στοιχεία πρέπει να είναι **σωστά τονισμένα**.
- Μόνο το **αρχικό γράμμα** μπορεί να είναι κεφαλαίο.
- Τα νησιά μπορούν να βρίσκονται είτε στο Αιγαίο είτε στο Ιόνιο πέλαγος, ενώ υπάρχουν και πόλεις που έχουν λιμάνια.

---

Ακολουθήστε τις παραπάνω οδηγίες για να διασφαλίσετε τη σωστή λειτουργία της εφαρμογής.

---

# Δομή Περιεχομένων

## Ριζικός Φάκελος
- **`.vscode/`**: Ρυθμίσεις για το Visual Studio Code.
- **`.VSCodeCounter/`**: Δεδομένα και αναφορές από το VSCodeCounter εργαλείο.
  - **Υποφάκελοι**:
    - **2024-11-25_18-36-56/**, **2024-11-25_18-44-25/**, κ.λπ.: Ειδικές αναφορές ανά ημερομηνία και ώρα.

## Φάκελος `shipdis/`
Ο κύριος φάκελος του project.
- **`src/`**: Περιέχει τον πηγαίο κώδικα και δοκιμές.
  - **`main/`**:
    - **`java/thecode/`**: Ο κύριος πηγαίος κώδικας της εφαρμογής.
    - **`resources/`**: Βοηθητικά αρχεία και πόροι της εφαρμογής.
  - **`test/`**:
    - **`java/thecode/`**: Δοκιμαστικός κώδικας για την εφαρμογή.
- **`target/`**: Φάκελος εξαγωγής και δεδομένων κατασκευής.
  - **`classes/thecode/`**: Παραχθείσες κλάσεις της εφαρμογής.
  - **`generated-sources/annotations/`**: Αυτόματα παραχθείσες πηγές.
  - **`generated-test-sources/test-annotations/`**: Παραχθείσες πηγές για δοκιμές.
  - **`maven-archiver/`**: Δεδομένα Maven για αρχειοθέτηση.
  - **`maven-status/`**: Στατιστικά Maven.
    - **`maven-compiler-plugin/`**:
      - **`compile/default-compile/`**: Αποτελέσματα της κύριας μεταγλώττισης.
      - **`testCompile/default-testCompile/`**: Αποτελέσματα μεταγλώττισης δοκιμών.
  - **`surefire-reports/`**: Αναφορές από το Surefire Plugin.
  - **`test-classes/thecode/`**: Παραχθείσες κλάσεις για τις δοκιμές.

## Φάκελος `src/`
- **`main/`**: Εναλλακτική τοποθεσία με πηγαίο κώδικα.

---

Αυτή η δομή αντιπροσωπεύει τα περιεχόμενα του αποθετηρίου και μπορεί να επεκταθεί με περισσότερες λεπτομέρειες όπου χρειάζεται.

---
## Διάγραμμα UML 
Εδώ φαίνεται η δομή του προγράμματος και συγκεκριμένα οι κλάσεις και οι μέθοδοι που περιλαμβάνονται

![Αναπαράσταση εικόνας](resources/UMLDIAGR.png)


---

## Δομή Δεδομένων

### Αρχείο InitializeGreekPorts.txt
Τα δεδομένα που χρησιμοποιεί η εφαρμογή είναι αποθηκευμένα στο αρχείο InitializeGreekPorts.txt που περιλαμβάνει για κάθε προορισμό: όνομα νησιού/πόλης, όνομα λιμανιού και τις συντεταγμένες του(πλάτος, μήκος). 

### Κλάση Port
Αναπαριστά το κάθε λιμάνι και τα 4 πεδία του

### Κλάση GreekPortCollection  
Δημιουργία λίστας ports που αποθηκεύει τα λιμάνια

Η μέθοδος InitializePorts() διαβάζει τα δεδομένα από το αρχέιο .txt
και στην συνέχεια δημιουργεί αντικείμενα τύπου Port για κάθε προορισμό.

---

## Αλγόριθμος Βελτιστοποίησης
 
Ο αλγόριθμος που χρησιμοποιείται είναι μια εκδοχή του αλγορίθμου Travelling Salesman Problem (TSP). 
Ξεκινώντας από την αφετηρία, αναζητάει κάθε φόρα τον κοντινότερο προορισμό, μέχρι να καλυφθούν όλα τα νήσια που χρειάζεται να περάσει η κρουαζιέρα.