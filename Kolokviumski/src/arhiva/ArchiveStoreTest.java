package arhiva;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class InvalidOpeningException extends Exception{
    public InvalidOpeningException(String message) {
        super(message);
    }
}

class NonExistingItemException extends Exception{
    public NonExistingItemException(String message) {
        super(message);
    }
}

abstract class Archive{
    private int id;
    private LocalDate dateArchived;

    public Archive(int id, LocalDate dateArchived) {
        this.id = id;
        this.dateArchived = dateArchived;
    }

    public Archive(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateArchived(LocalDate dateArchived) {
        this.dateArchived = dateArchived;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateArchived() {
        return dateArchived;
    }

    public abstract LocalDate open(int id, LocalDate date) throws InvalidOpeningException;
}

class LockedArchive extends Archive{
    private LocalDate dateToOpen;

    public LockedArchive(int id, LocalDate dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
    }

    @Override
    public LocalDate open(int id, LocalDate date) throws InvalidOpeningException {
        if (date.isBefore(dateToOpen)) {
            throw new InvalidOpeningException(String.format("Item %d cannot be opened before %s", id, dateToOpen));
        }
        return date;
    }


    public LocalDate getDateToOpen() {
        return dateToOpen;
    }

}

class SpecialArchive extends Archive{

    private int maxOpen;
    private int countOpen;
    public SpecialArchive(int id, int maxOpen) {
        super(id);
        this.maxOpen = maxOpen;
        countOpen=0;
    }

    public int getMaxOpen() {
        return maxOpen;
    }

    public int getCountOpen() {
        return countOpen;
    }



    @Override
    public LocalDate open(int id, LocalDate date) throws InvalidOpeningException {
        if (countOpen >= maxOpen) {
            throw new InvalidOpeningException(String.format("Item %d cannot be opened more than %d times", id, maxOpen));
        }
        countOpen++;
        return date;
    }

}

class ArchiveStore{
    private final List<Archive> archives;
    StringBuilder sb;

    public ArchiveStore(){
        archives=new ArrayList<>();
        sb = new StringBuilder();
    }

    public void archiveItem(Archive item, LocalDate date){
        item.setDateArchived(date);
        archives.add(item);
        sb.append(String.format("Item %d archived at %s\n", item.getId(), date));
    }


    public void openItem(int id, LocalDate date) throws NonExistingItemException {
        Archive item = archives.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NonExistingItemException(String.format("Item with id %d doesn't exist", id)));

        try {
            item.open(id, date);
            sb.append(String.format("Item %d opened at %s\n", id, date));
        } catch (InvalidOpeningException e) {
            sb.append(e.getMessage()).append("\n");
        }
    }


    public String getLog(){
        return sb.toString();
    }
}


public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        LocalDate date = LocalDate.of(2013, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();

            LocalDate dateToOpen = date.atStartOfDay().plusSeconds(days * 24 * 60 * 60).toLocalDate();
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}